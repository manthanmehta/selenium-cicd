pipeline{

    agent any

    stages{

        stage('Build Jar'){
            steps{
                echo "Building executable jars using maven clean package"
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Image'){
            steps{
                echo "Building an image using Jars in our DockerHub account"
                sh 'docker build -t=manthanmehtaa/selenium:lastest .'
            }
        }

        stage('Push Image'){
            environment{
                // assuming you have stored the credentials with this name
                DOCKER_HUB = credentials('bce5f27a-e07e-4da1-ad2c-7eb83c12e084')
            }
            steps{
                // There might be a warning.
                 sh 'docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}'
//                 sh 'echo ${DOCKER_HUB_PSW} | docker login -u {DOCKER_HUB_USR} --password-stdin'
                sh 'docker push manthanmehtaa/selenium:lastest'
                sh "docker tag manthanmehtaa/selenium:lastest manthanmehtaa/selenium:{$env.BUIlD_NUMBER}"
                 sh "docker push manthanmehtaa/selenium:${env.BUIlD_NUMBER}"
            }
        }
    }

        post {
            always{
                sh "docker logout"
            }

        }
}