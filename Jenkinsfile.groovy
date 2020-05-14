// Declarative, local deploy
pipeline {
    agent any
    stages {
        stage('Git Process') {
            steps {
                git credentialsId: 'd1818738-f9c0-499c-9136-f96c0df70a25',
                url: 'https://github.com/SuzyKimTech/SpringBoot_Jenkins.git'
            }
        }
        stage('Build') {
            steps {
                echo '!! Build start !!'
                sh './gradlew clean build'
            }
        }
        stage('Deploy on Remote server') {
            steps {
                echo '!! deploy on Local !!'
                sh 'whoami'
                sh '''
                    #!/bin/sh

                    PROJECT_PATH=/var/lib/jenkins/workspace/$JOB_NAME/build/libs
                    JAR_FILE=$PROJECT_PATH/demo-0.0.1-SNAPSHOT.jar
                    CURRENT_PID=$(ps -ef | grep java | grep demo* | awk '{print $2}')

                    if [ -z $CURRENT_PID ]; then
                    echo "> 현재 구동중인 application 없음"
                    else
                    echo "> kill application"
                    kill -9 $CURRENT_PID
                    fi

                    JENKINS_NODE_COOKIE=dontKillMe nohup java -Xms2048M -Xmx4096M -XX:+UseG1GC -XX:NewRatio=7 -verbose:gc -XX:+HeapDumpOnOutOfMemoryError -jar $JAR_FILE &
                    echo "> application 구동"

                '''
            }
        }
    }
}
