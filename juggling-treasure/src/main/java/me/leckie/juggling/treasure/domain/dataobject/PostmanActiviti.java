/**
 * BBD Service Inc All Rights Reserved @2018
 */
package me.leckie.juggling.treasure.domain.dataobject;

import java.util.List;

/**
 * @author laixianbo
 * @version $Id: PostmanActiviti.java, v0.1 2018/9/12 14:05 laixianbo Exp $$
 */
public class PostmanActiviti {

  private InfoBean info;

  private List<ItemBeanX> item;

  private List<VariableBean> variable;

  public InfoBean getInfo() {
    return info;
  }

  public void setInfo(InfoBean info) {
    this.info = info;
  }

  public List<ItemBeanX> getItem() {
    return item;
  }

  public void setItem(List<ItemBeanX> item) {
    this.item = item;
  }

  public List<VariableBean> getVariable() {
    return variable;
  }

  public void setVariable(List<VariableBean> variable) {
    this.variable = variable;
  }

  public static class InfoBean {

    /**
     * name : Activiti v7 REST API _postman_id : f2ef72c6-d066-0f76-6158-8ea4db8649c5 schema :
     * https://schema.getpostman.com/json/collection/v2.1.0/collection.json
     */

    private String name;
    private String _postman_id;
    private String schema;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String get_postman_id() {
      return _postman_id;
    }

    public void set_postman_id(String _postman_id) {
      this._postman_id = _postman_id;
    }

    public String getSchema() {
      return schema;
    }

    public void setSchema(String schema) {
      this.schema = schema;
    }
  }

  public static class ItemBeanX {





    private String name;
    private String description;
    private List<ItemBean> item;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public List<ItemBean> getItem() {
      return item;
    }

    public void setItem(List<ItemBean> item) {
      this.item = item;
    }

    public static class ItemBean {

      /**
       * name : getEvents request : {"method":"GET","header":[{"key":"Authorization","value":"Bearer
       * {{kcAccessToken}}"}],"body":{"mode":"raw","raw":""},"url":{"raw":"{{gatewayUrl}}/audit/v1/events","host":["{{gatewayUrl}}"],"path":["audit","v1","events"]}}
       * response : []
       */

      private String name;
      private RequestBean request;
      private List<?> response;

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public RequestBean getRequest() {
        return request;
      }

      public void setRequest(RequestBean request) {
        this.request = request;
      }

      public List<?> getResponse() {
        return response;
      }

      public void setResponse(List<?> response) {
        this.response = response;
      }

      public static class RequestBean {

        /**
         * method : GET header : [{"key":"Authorization","value":"Bearer {{kcAccessToken}}"}] body :
         * {"mode":"raw","raw":""} url : {"raw":"{{gatewayUrl}}/audit/v1/events","host":["{{gatewayUrl}}"],"path":["audit","v1","events"]}
         */

        private String method;
        private BodyBean body;
        private UrlBean url;
        private List<HeaderBean> header;

        public String getMethod() {
          return method;
        }

        public void setMethod(String method) {
          this.method = method;
        }

        public BodyBean getBody() {
          return body;
        }

        public void setBody(BodyBean body) {
          this.body = body;
        }

        public UrlBean getUrl() {
          return url;
        }

        public void setUrl(UrlBean url) {
          this.url = url;
        }

        public List<HeaderBean> getHeader() {
          return header;
        }

        public void setHeader(List<HeaderBean> header) {
          this.header = header;
        }

        public static class BodyBean {

          /**
           * mode : raw raw :
           */

          private String mode;
          private String raw;

          public String getMode() {
            return mode;
          }

          public void setMode(String mode) {
            this.mode = mode;
          }

          public String getRaw() {
            return raw;
          }

          public void setRaw(String raw) {
            this.raw = raw;
          }
        }

        public static class UrlBean {

          /**
           * raw : {{gatewayUrl}}/audit/v1/events host : ["{{gatewayUrl}}"] path : ["audit","v1","events"]
           */

          private String raw;
          private List<String> host;
          private List<String> path;

          public String getRaw() {
            return raw;
          }

          public void setRaw(String raw) {
            this.raw = raw;
          }

          public List<String> getHost() {
            return host;
          }

          public void setHost(List<String> host) {
            this.host = host;
          }

          public List<String> getPath() {
            return path;
          }

          public void setPath(List<String> path) {
            this.path = path;
          }
        }

        public static class HeaderBean {

          /**
           * key : Authorization value : Bearer {{kcAccessToken}}
           */

          private String key;
          private String value;

          public String getKey() {
            return key;
          }

          public void setKey(String key) {
            this.key = key;
          }

          public String getValue() {
            return value;
          }

          public void setValue(String value) {
            this.value = value;
          }
        }
      }
    }
  }

  public static class VariableBean {

    /**
     * id : d9204e75-9f07-4d2e-8e14-fccf42375439 key : gatewayUrl value : http://localhost:8080 type : text
     */

    private String id;
    private String key;
    private String value;
    private String type;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }
  }
}
