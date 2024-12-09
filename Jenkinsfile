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
                sh 'docker build -t=manthanmehtaa/selenium .'
            }
        }

        stage('Push Image'){
            environment{
                // assuming you have stored the credentials with this name
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps{
                // There might be a warning.
                sh 'docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}'
                sh 'docker push manthanmehtaa/selenium'
            }
        }
    }
}