pipeline {
    agent any
    stages{
        stage('Build gradle'){
            steps {
                sh './gradlew build'
            }
        }
        stage('Generate container'){
                    steps {
                        sh 'sudo docker login --username=lucasrafa94 --password=lucasr10'
                        sh 'sudo docker build . -t appsoapcorreios2:latest'
                    }
                }
        stage('Push to registry'){
                            steps {
                                sh 'sudo docker tag appsoapcorreios2:latest lucasrafa94/soapcorreios:latest'
                                sh 'sudo docker push lucasrafa94/soapcorreios:latest'
                            }
                        }
        stage('Deploy to Container Cloud'){
                                    steps {
                                         sh """ build=correios-test-`date +'%Y%m%d%H%M%s'` && \
                                                curl --insecure -X POST \
                                                  https://129.150.220.65/api/v2/deployments/ \
                                                  -H 'Authorization: Basic YWRtaW46TWFzdGVyIzEyMw==' \
                                                  -H 'Cache-Control: no-cache' \
                                                  -H 'Content-Type: application/json' \
                                                  -H 'Postman-Token: 670f2995-1e2f-34a0-c925-eef93707f393' \
                                                  -d '{"deployment_id":"'correios-test-`date +'%Y%m%d%H%M%s'`'","deployment_name":"'correios-test-`date +'%Y%m%d%H%M%s'`'","desired_state":1,"placement":{"pool_id":"default"},"quantities":{"nginx":1},"stack":{"service_id":"correios-test","service_name":"correios-test","subtype":"service"}}' """
                                    }
                                }
    }
}