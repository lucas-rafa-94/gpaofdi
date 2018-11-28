package com.ofdi.soap.models.yaml;

import java.util.List;

public class ConfigUtilsEss {

    public static class Services {
        private Jobs sendToLdap;
        private Jobs incentiveCompensationParticipantDetailImport;
        private Jobs assignRolesToParticipants;

        public Services(Jobs sendToLdap, Jobs incentiveCompensationParticipantDetailImport, Jobs assignRolesToParticipants) {
            this.sendToLdap = sendToLdap;
            this.incentiveCompensationParticipantDetailImport = incentiveCompensationParticipantDetailImport;
            this.assignRolesToParticipants = assignRolesToParticipants;
        }

        public Services() { }

        public Jobs getSendToLdap() {
            return sendToLdap;
        }

        public void setSendToLdap(Jobs sendToLdap) {
            this.sendToLdap = sendToLdap;
        }

        public Jobs getIncentiveCompensationParticipantDetailImport() {
            return incentiveCompensationParticipantDetailImport;
        }

        public void setIncentiveCompensationParticipantDetailImport(Jobs incentiveCompensationParticipantDetailImport) {
            this.incentiveCompensationParticipantDetailImport = incentiveCompensationParticipantDetailImport;
        }

        public Jobs getAssignRolesToParticipants() {
            return assignRolesToParticipants;
        }

        public void setAssignRolesToParticipants(Jobs assignRolesToParticipants) {
            this.assignRolesToParticipants = assignRolesToParticipants;
        }
    }

    public static class Jobs {
        private String jobDefinitionName;
        private String packageName;
        private List<String> arguments;
        private List<Integer> inputs;

        public Jobs(String jobDefinitionName, String packageName, List<String> arguments, List<Integer> inputs) {
            this.jobDefinitionName = jobDefinitionName;
            this.packageName = packageName;
            this.arguments = arguments;
            this.inputs = inputs;
        }

        public Jobs() { }

        public String getJobDefinitionName() {
            return jobDefinitionName;
        }

        public void setJobDefinitionName(String jobDefinitionName) {
            this.jobDefinitionName = jobDefinitionName;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public List<String> getArguments() {
            return arguments;
        }

        public void setArguments(List<String> arguments) {
            this.arguments = arguments;
        }

        public List<Integer> getInputs() {
            return inputs;
        }

        public void setInputs(List<Integer> inputs) {
            this.inputs = inputs;
        }
    }


    public static class LocalPart {
        private String getESSJobStatus;
        private String submitESSJobRequest;

        public LocalPart(String getESSJobStatus, String submitESSJobRequest) {
            this.getESSJobStatus = getESSJobStatus;
            this.submitESSJobRequest = submitESSJobRequest;
        }

        public LocalPart() { }

        public String getGetESSJobStatus() {
            return getESSJobStatus;
        }

        public void setGetESSJobStatus(String getESSJobStatus) {
            this.getESSJobStatus = getESSJobStatus;
        }

        public String getSubmitESSJobRequest() {
            return submitESSJobRequest;
        }

        public void setSubmitESSJobRequest(String submitESSJobRequest) {
            this.submitESSJobRequest = submitESSJobRequest;
        }
    }

    public static class Xmlns {
        private String value;
        private String prefix;

        public Xmlns(String value, String prefix) {
            this.value = value;
            this.prefix = prefix;
        }

        public Xmlns() {
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }
    }
}
