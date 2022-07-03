import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition;
import hudson.model.*;
  
def jobName = 'Test_Script_Groovy'

script = '''
pipeline {
    agent any
    options { disableConcurrentBuilds() }
    environment {
        GitBranch = "main"
        EC2_IP = "$EC2_IP_Script"
        ENV = "$ENV_Script"
        BOOL = "$BOOL_Script"
        TXT = "$TXT_Script"
    }
    stages {
        stage ("Clean Jenkins workspace before start") {
            steps {
                cleanWs()
            }
        }

        stage ("Echo var") {
            steps {
                sh "pwd"
                sh "echo $EC2_IP"
                sh "echo $ENV"
                sh "echo $BOOL"
                sh "echo $TXT"
                
            }
        }
    }
}
'''
// Create Project
p = Jenkins.instance.createProject(WorkflowJob, jobName)
p.setDefinition(new CpsFlowDefinition(script, false))
p.save()

// Create Params
def params = []

// Assign Params
params += new StringParameterValue('EC2_IP_Script', '0.0.0.0')
params += new StringParameterValue('ENV_Script', 'dev')
params += new StringParameterValue('BOOL_Script', 'false')
params += new StringParameterValue('TXT_Script', 'Lorem Ipsum')

def paramsAction = new ParametersAction(params)

// Schedule Project
Hudson.instance.queue.schedule(p, 0, null, paramsAction)