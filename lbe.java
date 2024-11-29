package org.dinky.executor;

import org.dinky.classloader.DinkyClassLoader;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.PipelineOptions;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.hutool.core.io.FileUtil;

/**
 * LocalBatchExecutor
 *
 * @since 2022/2/4 0:04
 */
public class LocalBatchExecutor extends Executor {

    public LocalBatchExecutor(ExecutorConfig executorConfig, DinkyClassLoader classLoader) {
        this.executorConfig = executorConfig;
        if (executorConfig.isValidJarFiles()) {
            executorConfig
                    .getConfig()
                    .put(
                            PipelineOptions.JARS.key(),
                            Stream.of(executorConfig.getJarFiles())
                                    .map(FileUtil::getAbsolutePath)
                                    .collect(Collectors.joining(",")));
        }
        if (!executorConfig.isPlan()) {
            Configuration configuration = Configuration.fromMap(executorConfig.getConfig());
            if (!configuration.contains(RestOptions.PORT)) {
                configuration.set(RestOptions.PORT, executorConfig.getPort());
            }
            this.environment = StreamExecutionEnvironment.createLocalEnvironment(configuration);
        } else {
            this.environment = StreamExecutionEnvironment.createLocalEnvironment();
        }
        init(classLoader);
    }

    @Override
    CustomTableEnvironment createCustomTableEnvironment(ClassLoader classLoader) {
        return CustomTableEnvironmentImpl.createBatch(environment, classLoader);
    }
}
