import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition;
import hudson.model.*;
  
def job1 = 'Build_AMI'

// Pipeline
script = '''
pipeline {
    agent any
    options { disableConcurrentBuilds() }
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

        stage ("checkout_git") {
            steps {
                sh "pwd"
                dir("Build_WebAMI") {
                    sh "pwd"
                    git(
                        url: "git@github.com:AntoineRecover/PipelinesProject.git",
                        credentialsId: "$GitCredentials",
                        branch: "${GitBranch}"
                    )
                    sh "ls"
                }
            }
        }

        stage ("Build AMI") {
            steps {
                dir("Build_WebAMI") {
                    dir("Packer") {
                        sh "pwd"
                        sh "ls"
                        
                        wrap([$class: "AnsiColorBuildWrapper", "colorMapName": "xterm"]){
                            sh "packer init ."
                            sh "packer fmt ."
                            sh "packer validate ."
                            sh "packer build -var region=$region -var subnet_id=$subnet_id -var ami_name=$project_name -var port=$port -var pcks=$pcks -var env=$ENV . "
                        }
                    }
                }
            }
        }
    }
}
'''

p = Jenkins.instance.createProject(WorkflowJob, job1)
p.setDefinition(new CpsFlowDefinition(script1, false))
p.save()

def params1 = []
params1 += new StringParameterValue('ENV', 'dev')
params1 += new StringParameterValue('EC2IP', '13.38.80.144')
params1 += new StringParameterValue('GitCredentials', 'GitCred')
params1 += new StringParameterValue('PROJECT_NAME', 'Build_AMI_Amelioration')
def pA1 = new ParametersAction(params1)

Hudson.instance.queue.schedule(p, 0, null, pA1)