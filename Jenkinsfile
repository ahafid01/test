pipeline {
  agent any
  stages {
    stage('compile 1') {
      steps {
        sh 'javac src/Main.java'
      }
    }

    stage('execute 1') {
      steps {
        sh 'java src/Main.java'
      }
    }

  }
}
