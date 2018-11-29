# GPAOFDI

## RESUMO

Para a resolução do problema utilizamos e criamos uma aplicação Spring Boot, com suas dependêcias gerenciadas via Gradle.
Tudo foi desenvolvido utilizando a ide Intellij, mas qualquer ide focada em Java é capaz de abrir o projeto (Eclipse, Netbeans).
Segue abaixo os passos necessários para que a aplicação rode seguindo as premissas.

## BIBLIOGRAFIA

- Spring Boot: https://spring.io/
- Gradle: https://gradle.org/
- Intellij: https://www.jetbrains.com/idea/

## REQUISITOS
- Máquina Windows/Linux
- Java 8
- Banco de dados MySql

### PASSO 1 - CRIAÇÃO DO BANCO

Como pedido a aplicação insere seus dados de estado em um banco de dados MySql, e primeiramente esse banco deve estar disponível.
Após isso, solicitamos que o arquivo "script.sql" encontrado nesse mesmo projeto na pasta "entregavel" seja executado. Nesse script esta presente
a criação do banco, usuário e a tabela com que a aplicação irá conversar.

### PASSO 2 - CONFIGURAÇÃO PROPERTIES RELACIONADO AO BANCO

Com o banco/tabela em ordem, devemos alterar o arquivo "application.properties" encontrados no "/src/main/resources/application.properties"
a fim de que comporte o novo banco criado, seguindo o exemplo abaixo:

```
spring.datasource.url=jdbc:mysql://localhost:3306/gpadb
spring.datasource.username=gpauser
spring.datasource.password=Welcome#1
```

### PASSO 3 - BUILD APLICAÇÃO

Com isso já podemos fazer build de nossa aplicação e gerar o jar que será executado diariamente, seguindo esses comandos:

```
$ gradle build - "Compila e baixa as dependências, caso alguma esteja faltando"
$ gradle bootJar - "Gera o jar executável, esse será encontrado na pasta 'build/libs'"
```

Obs:. Caso sua Ide não possua plugins Gradle, o Gradle deverá estar instalado na máquina.

### PASSO 4 - GERAR .BAT/.SH

Uma alternativa para que não seja necessário rodar o .jar direto disponibilizamos templates de .bat (Windows) e .sh (Unix/Linux),
ambos dispostos na pasta "entregavel". Fiquem a vontade para customizar para atender as demandas de gerenciamento da atividade.

run.bat

```
@echo off
java -jar gpaofdi-0.1.jar
```

run.sh

```
#!/bin/bash 
java -jar gpaofdi-0.1.jar
```

## CONFIGURAÇÃO PARÂMETROS

Todos os parâmetros utilizados nos jobs estão presentes e sendo parametrizados de forma externa do código Java.
Estão presentes no arquivo "application.yml" encontrado no diretório "/src/main/resources/application.yml"

application.yml

```
folder:
  path: /Users/lucasdossantos/Desktop/gpaCsv/testefinal/
  csvChefe: user_import_chefe.csv
  csvVendedor: user_import_vendedor.csv
  zipChefe: IcParticipantDetailChefe.zip
  zipVendedor: IcParticipantDetailVend.zip
  zipGoalChefe: IcGoalImportChefe.zip
  zipGoalVendedor: IcGoalImportVend.zip
client:
  defaultUriIntegrationService: https://ehhs-test.fa.la1.oraclecloud.com:443/fscmService/ErpIntegrationService
  defaultUriMktImport: https://ehhs-test.fa.la1.oraclecloud.com:443/crmService/ImportPublicService
  user:
    name: marcelo.taboada@oracle.com
    password: gpa12345
mktimport:
  defaultUri: https://ehhs-test.fa.la1.oraclecloud.com:443/crmService/ImportPublicService
  submitImportActivityChefe:
    xmlnsFather:
      name: http://xmlns.oracle.com/oracle/apps/marketing/commonMarketing/mktImport/model/types/
      prefix: typ
      localPart:
         - submitImportActivity
    xmlnsChild:
      name: http://xmlns.oracle.com/oracle/apps/marketing/commonMarketing/mktImport/model/
      prefix: mod
      localPart:
        - HeaderRowIncluded
        - MappingNumber
        - Delimiter
        - FileFormat
        - FileContent
      values:
        - Y
        - 300000003723223
        - ;
        - OTHER_DELIMITER
  submitImportActivityVendedor:
    xmlnsFather:
      name: http://xmlns.oracle.com/oracle/apps/marketing/commonMarketing/mktImport/model/types/
      prefix: typ
      localPart:
        - submitImportActivity
    xmlnsChild:
      name: http://xmlns.oracle.com/oracle/apps/marketing/commonMarketing/mktImport/model/
      prefix: mod
      localPart:
        - HeaderRowIncluded
        - MappingNumber
        - Delimiter
        - FileFormat
        - FileContent
      values:
        - Y
        - 300000003723398
        - ;
        - OTHER_DELIMITER
  getImportActivityStatus:
    xmlnsFather:
      name: http://xmlns.oracle.com/oracle/apps/marketing/commonMarketing/mktImport/model/types/
      prefix: typ
      localPart:
        - getImportActivityStatus
      values:
    xmlnsChild:
      name:
      prefix:
      localPart:
      values:
uploadfiletoucm:
  defaultUri: https://ehhs-test.fa.la1.oraclecloud.com:443/fscmService/ErpIntegrationService
  upload:
    xmlnsFather:
      name: http://xmlns.oracle.com/apps/financials/commonModules/shared/model/erpIntegrationService/types/
      prefix: erp
      localPart:
        - uploadFileToUcm
      values:
    xmlnsChild:
      name: http://xmlns.oracle.com/apps/financials/commonModules/shared/model/erpIntegrationService/
      prefix: erp
      localPart:
        - DocumentTitle
        - DocumentAuthor
        - DocumentSecurityGroup
        - DocumentAccount
        - DocumentName
      values:
ess:
  defaultUri: https://ehhs-test.fa.la1.oraclecloud.com:443/fscmService/ErpIntegrationService
  xmlns:
    value: http://xmlns.oracle.com/apps/financials/commonModules/shared/model/erpIntegrationService/types/
    prefix: typ
  localPart:
    getESSJobStatus: getESSJobStatus
    submitESSJobRequest: submitESSJobRequest
  services:
    sendToLdap:
      jobDefinitionName: CopyPersonalData
      packageName: /oracle/apps/ess/hcm/users/
      arguments: ALL_USERS
      inputs:
    importIncentiveCompensationParticipants:
      jobDefinitionName: ParticipantsImportSQL
      packageName: /oracle/apps/ess/incentiveCompensation/cn/processes/transactionProcess/
      arguments: 300000001064232, '#NULL', '#NULL', PARTICIPANT, '#NULL', '#NULL', '#NULL', '#NULL', '#NULL', '#NULL', '#NULL', '#NULL', '#NULL', '#NULL', '#NULL', 'HR_EMPLOYEE'
      inputs:
    incentiveCompensationParticipantDetailImport:
      jobDefinitionName: SrpInterfaceLoader
      packageName: /oracle/apps/ess/incentiveCompensation/cn/processes/transactionProcess/
      arguments: archive, idArchive, N
      inputs: 0, 1
    importParticipantsGoals:
      jobDefinitionName: SrpGoalInterfaceLoader
      packageName: /oracle/apps/ess/incentiveCompensation/cn/processes/transactionProcess/
      arguments: archive, idArchive, N
      inputs: 0, 1
    assignRolesToParticipants:
      jobDefinitionName: ParticipantAssignment
      packageName: /oracle/apps/ess/incentiveCompensation/cn/processes/setupProcess/
      arguments: 300000001064232, date, date, NEW, ROLE_ASGN, '#NULL'
      inputs: 1, 2
utils:
  file: zip
  accountparticipant: ic$/incentiveCompensationParticipant$/import$
  accountgoal: ic$/incentiveCompensationParticipantGoal$/import$
  role: FAFusionImportExport
  switch: N
  threadsleep: 36000
incentivecompensation:
  mapping: 300000001064232
  param1: NEW
  param2: ROLE_ASGN
  param3: '#NULL'
```
Qualquer alteração no valor dos parâmetros desse arquivo, será necessário a execução do PASSO 3 - BUILD APLICAÇÃO novamente.


