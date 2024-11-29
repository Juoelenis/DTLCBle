package org.dinky.service;

import org.dinky.data.vo.CascaderVO;

import java.util.List;

public interface FlinkService {

    List<CascaderVO> loadConfigOptions();
}
