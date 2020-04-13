pipeline {
  agent any
  stages {
    stage('compile') {
      steps {
        sh 'javac Main'
      }
    }

    stage('execute') {
      steps {
        sh 'java Main'
      }
    }

  }
}