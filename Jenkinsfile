pipeline {
    environment {
        imageName = 'forecast:latest'
        registryCredentialSet = 'dockerhub'
    }
    agent any
    tools {
        maven 'maven-3.6.3'
        jdk 'JDK'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }
        stage('Cloning Git') {
          steps {
            git 'https://github.com/errajesh29/weatherForecastRepo.git'
          }
        }
        stage ('Build Docker Image') {
            steps {
                echo "Docker Image Tag Name: " + imageName
                sh "docker build -t " + imageName + " ."
            }
        }
        stage ('Deploy Docker Image Locally') {
            steps {
              sh '''
                  docker ps -a \
                    | awk '{ print \$1,\$2 }' \
                    | grep forecast:latest \
                    | awk '{print \$1 }' \
                    | xargs -I {} docker rm -f {}
                  '''
              sh "docker run --name forecast -d -p 3000:3000 " + imageName
            }
        }
    }
}