source "amazon-ebs" "web-instance" {
  region                      = "${var.region}"
  subnet_id                   = "${var.subnet_id}"
  ami_name                    = "${var.ami_name}"
  source_ami                  = "${var.base_ami}"
  instance_type               = "${var.instance_type}"
  associate_public_ip_address = true

  tags {
    Name = "${var.ami_name}"
  }

}