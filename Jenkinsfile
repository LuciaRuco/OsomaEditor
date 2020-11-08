pipeline {
    agent any

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
