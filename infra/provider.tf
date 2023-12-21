provider "google" {
  credentials = var.gcp_sa_credentials
  project = var.gcp_project
  region  = var.gcp_region
}
