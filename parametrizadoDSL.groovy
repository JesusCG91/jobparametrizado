job('ejemplo-job-DSL2') {
  description('Job DSL de ejemplo para el curso de Jenkins')
  scm {
    git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node ->
      node / gitConfigName('jesus')
      node / gitConfigEmail('jcastrog0212@gmail.com')
    }
  }
  parameters {
    stringParam('nombre', defaultValue = 'Jesus', description = 'Parametro de dacena para el job booleano')
	choiceParam('planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Seturno', 'Urano', 'Neptuno'])
    booleanParam('agente', false)
  }
  triggers {
  	cron('H/7 * * * *')
  }
  steps{
    shell("bash jobscript.sh")
  }
  publishers{
    mailer('jcastrog0212@gmail.com', true, true)
    slackNotifier{
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
