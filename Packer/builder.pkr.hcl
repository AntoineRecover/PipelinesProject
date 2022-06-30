build {
  name = "web-instance-builder"
  source "source.amazon-ebs.web-instance" {
    ssh_username = "ubuntu"
  }
  provisioner "ansible" {
    playbook_file = "./play.yml"
    extra_arguments = [ "--extra-vars", "pcks=${var.pcks} port=${var.port}" ]
  }
}