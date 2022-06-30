terraform {
  backend "s3" {
    bucket = ""
    key = ""
    region = ""
  }
}

variable "env" {
  type    = string
  default = "dev"
}

variable "region" {
  type = string
  default = "eu-west-3"
}

variable "ami_name" {
    type = string
    default = "PackerAnsible-Apache"
}

variable "port" {
    type = number
    default = 80
}