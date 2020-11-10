pipeline {
    agent any
    tools {
            maven 'maven_3_5_0'
            jdk 'jdk'
    }

    stages {
        stage ('Compile Stage'){
            step{
                withMaven (maven : 'maven_3_6_3'){
                    sh 'mvn clean compile'
                }
            }
        }
        stage ('Testing Stage'){
            step{
                withMaven (maven : 'maven_3_6_3'){
                    sh 'mvn test'
                }
            }
        }

        stage ('Deployment Stage'){
            step{
                withMaven (maven : 'maven_3_6_3'){
                sh 'mvn deploy'
                }
            }
        }
    }
}




pipeline {
    agent any


    tools {
        maven 'maven_3_5_0'
        jdk 'jdk'
    }

    stages {

        stage('Install') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/LuciaRuco/OsomaEditor.git'

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    //junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
