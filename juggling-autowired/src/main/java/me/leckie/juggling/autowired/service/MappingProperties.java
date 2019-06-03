package me.leckie.juggling.autowired.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Leckie
 * @version 1: MappingProperties.java, v0.1 2019/5/31 12:29 john Exp $$
 */
@Component
@ConfigurationProperties(prefix = "mapping")
public class MappingProperties {

  private Logger logger = LoggerFactory.getLogger(MappingProperties.class);

  private Map<String, String> machineNameMap = new LinkedHashMap<>();

  private Map<String, String> productCodeAreaMap = new LinkedHashMap<>();

  private Map<String, String> productCodeMap = new LinkedHashMap<>();

  private Map<String, String> fieldMapping = new LinkedHashMap<>();

  private List<Map<String, String>> list = new LinkedList<>();

  @PostConstruct
  private void postConstruct() {
    logger.info("machineNameMap={}", machineNameMap);
    logger.info("productCodeAreaMap={}", productCodeAreaMap);
    logger.info("productCodeMap={}", productCodeMap);
    logger.info("fieldMapping={}", fieldMapping);
    logger.info("list={}", list);
  }

  public Map<String, String> getMachineNameMap() {
    return machineNameMap;
  }

  public void setMachineNameMap(Map<String, String> machineNameMap) {
    this.machineNameMap = machineNameMap;
  }

  public Map<String, String> getProductCodeAreaMap() {
    return productCodeAreaMap;
  }

  public void setProductCodeAreaMap(Map<String, String> productCodeAreaMap) {
    this.productCodeAreaMap = productCodeAreaMap;
  }

  public Map<String, String> getProductCodeMap() {
    return productCodeMap;
  }

  public void setProductCodeMap(Map<String, String> productCodeMap) {
    this.productCodeMap = productCodeMap;
  }

  public Map<String, String> getFieldMapping() {
    return fieldMapping;
  }

  public void setFieldMapping(Map<String, String> fieldMapping) {
    this.fieldMapping = fieldMapping;
  }

  public List<Map<String, String>> getList() {
    return list;
  }

  public void setList(List<Map<String, String>> list) {
    this.list = list;
  }
}
