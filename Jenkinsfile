pipeline {
  agent any
  stages {
    stage('compile 1') {
      steps {
        sh 'javac Main'
      }
    }

    stage('execute 1') {
      steps {
        sh 'java Main'
      }
    }

  }
}
