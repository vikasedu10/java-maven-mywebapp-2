def buildApp() {
    echo "Building the application"
    sh "mvn -B -DskipTests clean package"
}

def buildImage() {
    echo "Build Image"
    sh "docker build -t ${IMAGE_NAME} ."
}

def testApp() {
    echo "Testing app"
    sh "mvn test"
}

def deployImagetoDockerHub() {
    echo "Deploying to docker-hub"
    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
        sh "echo ${PASSWORD} | docker login -u ${USERNAME} --password-stdin"
        sh "docker push ${IMAGE_NAME}"
    }
}

def deployImageToEC2() {
    echo "Doploying to ec2"
    def DOCKER_RUN_COMMAND = "docker run ${IMAGE_NAME}"
    
    sshagent(['ec2-ssh-keypair']) {
        sh "ssh -o StrictHostKeyChecking=no ${EC2_USERNAME}@${IP_ADDRESS} ${DOCKER_RUN_COMMAND}" 
    }
}


return this