pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }
    
    environment {
        APP_NAME = 'jenkins-demo'
        VERSION = "${BUILD_NUMBER}"
        DOCKER_IMAGE = "${APP_NAME}:${VERSION}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo '🔍 Clonando repositorio...'
                checkout scm
                sh 'git rev-parse --short HEAD > .git/commit-id'
                script {
                    env.GIT_COMMIT_ID = readFile('.git/commit-id').trim()
                }
            }
        }
        
        stage('Build') {
            steps {
                echo '🔨 Compilando aplicación...'
                sh 'mvn clean compile'
            }
        }
        
        stage('Unit Tests') {
            steps {
                echo '🧪 Ejecutando pruebas unitarias...'
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Code Analysis') {
            steps {
                echo '📊 Analizando calidad de código...'
                sh 'mvn verify'
            }
        }
        
        stage('Package') {
            steps {
                echo '📦 Empaquetando aplicación...'
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        
        stage('Docker Build') {
            when {
                branch 'main'
            }
            steps {
                echo '🐳 Construyendo imagen Docker...'
                script {
                    sh """
                        echo 'Simulando construcción de imagen Docker...'
                        echo 'docker build -t ${DOCKER_IMAGE} .'
                    """
                }
            }
        }
        
        stage('Deploy to Dev') {
            when {
                branch 'main'
            }
            steps {
                echo '🚀 Desplegando en ambiente de desarrollo...'
                script {
                    sh """
                        echo 'Desplegando versión ${VERSION}'
                        echo 'Commit: ${GIT_COMMIT_ID}'
                        # Aquí irían los comandos reales de deploy
                    """
                }
            }
        }
        
        stage('Integration Tests') {
            when {
                branch 'main'
            }
            steps {
                echo '🔬 Ejecutando pruebas de integración...'
                sh 'mvn verify -Pintegration-tests || true'
            }
        }
        
        stage('Deploy to Production') {
            when {
                branch 'main'
            }
            steps {
                input message: '¿Desplegar a producción?', ok: 'Desplegar'
                echo '🎯 Desplegando a producción...'
                script {
                    sh """
                        echo 'Desplegando a producción...'
                        echo 'Versión: ${VERSION}'
                    """
                }
            }
        }
    }
    
    post {
        success {
            echo '✅ Pipeline ejecutado exitosamente!'
            // Aquí podrías enviar notificaciones (Slack, email, etc.)
        }
        failure {
            echo '❌ Pipeline falló!'
            // Notificación de fallo
        }
        always {
            echo '🧹 Limpiando workspace...'
            cleanWs()
        }
    }
}
