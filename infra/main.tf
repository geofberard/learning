resource "google_service_account" "sa_api_deployment" {
  account_id   = "api-deployment"
  display_name = "Service Account to deploy Api in AppEngine"
}

resource "google_project_service" "enabled_service" {
  for_each = toset([
    "appengine.googleapis.com",
    "cloudbuild.googleapis.com",
    "iam.googleapis.com",
  ])
  project = var.gcp_project
  service = each.key
}

resource "google_service_account" "sa_api_runtime" {
  account_id   = "api-runtime"
  display_name = "Service Account used by Api in AppEngine"
}

resource "google_project_iam_member" "appengine_deployer_roles" {
  for_each = toset([
    "roles/appengine.deployer",
    "roles/cloudbuild.builds.builder",
    "roles/storage.objectAdmin",
  ])
  project = var.gcp_project
  role    = each.key
  member  = "serviceAccount:${google_service_account.sa_api_deployment.email}"
}

resource "google_service_account_iam_binding" "appengine_deployer_impersonate_runtime" {
  service_account_id = google_service_account.sa_api_runtime.name
  role               = "roles/iam.serviceAccountUser"

  members = [
    "serviceAccount:${google_service_account.sa_api_deployment.email}",
  ]
}

resource "google_app_engine_application" "learning-system-prod" {
  project     = var.gcp_project
  location_id = var.gcp_region
}

