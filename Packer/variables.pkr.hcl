variable "region" {
  type    = string
  default = "eu-west-3"
}

variable "instance_type" {
  type    = string
  default = "t2.micro"
}

variable "ami_name" {
  type    = string
  default = "web-instance"
}

variable "base_ami" {
  type    = string
  default = "ami-03214270b6681ff5e"
}

variable "subnet_id" {
  type    = string
  default = "subnet-03526017b01a32b7a"
}

 variable "pcks"{
  type    = string
  default = "python3,git,apache2"
 }