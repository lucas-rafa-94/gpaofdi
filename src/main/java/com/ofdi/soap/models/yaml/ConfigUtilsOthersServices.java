package com.ofdi.soap.models.yaml;

import java.util.List;

public class ConfigUtilsOthersServices {

    public static class XmlnsCustom {
        private String name;
        private String prefix;
        private List<String> localPart;
        private List<String> values;
        private ServiceCustom upload;

        public XmlnsCustom(String name, String prefix, List<String> localPart, List<String> values, ServiceCustom upload) {
            this.name = name;
            this.prefix = prefix;
            this.localPart = localPart;
            this.values = values;
            this.upload = upload;
        }

        public XmlnsCustom() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public List<String> getLocalPart() {
            return localPart;
        }

        public void setLocalPart(List<String> localPart) {
            this.localPart = localPart;
        }

        public List<String> getValues() {
            return values;
        }

        public void setValues(List<String> values) {
            this.values = values;
        }

        public ServiceCustom getUpload() {
            return upload;
        }

        public void setUpload(ServiceCustom upload) {
            this.upload = upload;
        }
    }

        public static class ServiceCustom {
            private XmlnsCustom xmlnsFather;
            private XmlnsCustom xmlnsChild;

            public ServiceCustom(XmlnsCustom xmlnsFather, XmlnsCustom xmlnsChild) {
                this.xmlnsFather = xmlnsFather;
                this.xmlnsChild = xmlnsChild;
            }

            public ServiceCustom() {
            }

            public XmlnsCustom getXmlnsFather() {
                return xmlnsFather;
            }

            public void setXmlnsFather(XmlnsCustom xmlnsFather) {
                this.xmlnsFather = xmlnsFather;
            }

            public XmlnsCustom getXmlnsChild() {
                return xmlnsChild;
            }

            public void setXmlnsChild(XmlnsCustom xmlnsChild) {
                this.xmlnsChild = xmlnsChild;
            }
        }
    }
