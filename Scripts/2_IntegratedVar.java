pipeline {
    agent any
    environment {
        GitBranch = "main"
        EC2_IP = "$EC2IP"
    }

    stages {
        stage ("Clean Jenkins workspace before start") {
            steps {
                cleanWs()
            }
        }


        stage("Parametrage") {
            steps {
                script { 
                    properties([
                        parameters([
                            choice(
                                choices: ['dev', 'prod', 'preprod'],
                                description: '',
                                name: 'ENV_Script'
                            ),
                            booleanParam(
                                defaultValue: true, 
                                description: '', 
                                name: 'BOOLEAN_Script'
                            ),
                            text(
                                defaultValue: 'Lorem ipsum dolor sit amet',
                                description: '',
                                name: 'TEXT_Script'
                            ),
                            string(
                                defaultValue: '13.38.80.144:8080',
                                description: '', 
                                name: 'EC2_IP_Script', 
                                trim: true
                            )
                        ])
                    ])
                }
            }
        }
        
        stage ("Echo var") {
            steps {
                sh "pwd"
                sh "echo $EC2_IP_Script"
                sh "echo $ENV_Script"
                sh "echo $BOOLEAN_Script"
                sh "echo $TEXT_Script"
            }
        }
    }
}