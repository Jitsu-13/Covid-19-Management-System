pipeline {
    agent any
    
    stages {
        
        stage('Build') {
            steps {
                // Build the Spring Boot project
            
                
                // Or, if you have a specific Maven command, use:
                sh 'mvn clean install'
            }
        }
        
        stage('Test') {
            steps {
                // Run tests for the Spring Boot project
        
                
                // Or, if you have a specific Maven command, use:
                sh 'mvn test'
            }
        }
    }
    
    post {
        always {
            // Archive the build artifacts (e.g., JAR file)
            archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
        }
        success {
            // Publish JUnit test results
            junit 'build/reports/tests/**/*.xml'
        }
    }
}
