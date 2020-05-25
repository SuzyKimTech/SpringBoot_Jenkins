pipeline {
  agent any
  stages {
    stage('Git Process') {
      parallel {
        stage('Git Process') {
          steps {
            git(credentialsId: 'd1818738-f9c0-499c-9136-f96c0df70a25', url: 'https://github.com/SuzyKimTech/SpringBoot_Jenkins.git')
          }
        }

        stage('') {
          steps {
            input(message: 'hey', id: '11', ok: 'ok?', submitter: 'suzy', submitterParameter: 'suzy')
          }
        }

      }
    }

    stage('Build') {
      steps {
        echo '!! Build start !!'
        sh './gradlew clean build'
      }
    }

    stage('Send file to Remote server & Deploy') {
      steps {
        script {
          sshPublisher(
            publishers: [
              sshPublisherDesc(
                configName: 'remote',
                transfers: [
                  sshTransfer(
                    cleanRemote: false,
                    excludes: '',
                    execCommand: '/bin/bash /home/centos/script/demoDeploy.sh',
                    execTimeout: 120000,
                    flatten: false,
                    makeEmptyDirs: false,
                    noDefaultExcludes: false,
                    patternSeparator: '[, ]+',
                    remoteDirectory: '/deploy',
                    remoteDirectorySDF: false,
                    removePrefix: 'build/libs',
                    sourceFiles: 'build/libs/*.jar',
                    usePty: true
                  )
                ],
                usePromotionTimestamp: false,
                useWorkspaceInPromotion: false,
                verbose: false
              )
            ]
          )
        }

      }
    }

  }
}