List<String> pipelines = Arrays.asList("Build_AMI", "Deploy_Infra", "Deploy_WebSite", "Build_ALL")
List<String> d_pipelines = Arrays.asList("Destroy_AMI", "Destroy_Infra", "Destroy_WebSite")

for (String pipeline : pipelines) {
    def job = hudson.model.Hudson.instance.getJob(pipeline)
    hudson.model.Hudson.instance.queue.schedule(job, 0)
}

for (String d_pipeline : d_pipelines) {
    def job = hudson.model.Hudson.instance.getJob(d_pipeline)
    hudson.model.Hudson.instance.queue.schedule(job, 0)
}