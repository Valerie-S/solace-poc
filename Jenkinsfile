import java.util.regex.*

JENKINS_WORKSPACE = "dp-node"
VERSION = "1.0.0-SNAPSHOT"
BRANCH_NAME = "${env.BRANCH_NAME}"
NEXUS_URL_RELEASE = "http://w360tm1.mdc.cginet:8081/repository/w360-tp-release"
NEXUS_URL_SNAPSHOT = "http://w360tm1.mdc.cginet:8081/repository/w360-tp-snapshot"
PROJECT_NAME = "wealth-spring-boot-solace"
DEPLOY_SERVER = "W360DP1"
SERVER_USER = "dpmgr"
DEPLOY_PATH = "/usr/tomcat"

node("$JENKINS_WORKSPACE"){
    // try { // Enable this try and catch block if you want to send out email and slack notification
        stage('Checkout branch') {
            checkout scm;
        }
        stage('Maven Build with Sonar'){
            dir("$WORKSPACE"){
                withSonarQubeEnv('SonarQubeServer'){
                    // check for pull request
                    if(Pattern.compile("^PR-[0-9]+").matcher("${BRANCH_NAME}").find()){
                        sh 'mvn -v; ' +
                                'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent -U -e install sonar:sonar -Dsonar.branch.name=$BRANCH_NAME'
                    } else {
                        sh 'mvn -v; ' +
                                'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent -U -e deploy sonar:sonar'
                    }
                }
            }
        }
        stage("Quality Gate"){
            sleep 20  //pause to wait for sonarqube to return sonar test status back to jenkins
            timeout(2) {
                def qualitygate = waitForQualityGate()
                if (qualitygate.status != 'OK'){
                    error "Pipeline aborted due to quality gate failure: ${qualitygate.status}"
                }
            }
        }
    /*} catch (Exception e) { // Enable this try and catch block if you want to send out email and slack notification
        currentBuild.result = "FAILED"
        emailext mimeType: 'text/html', subject: "BUILD ${env.BUILD_TAG} FAILED", body: "<p>Check build at: ${env.BUILD_URL}</p>", to: "$EMAIL_RECIPIENTS"
        // Slack channel to publish the notification to. Replace this url with the desired slack channel URL.
        slackSend baseUrl: 'https://cgi-global-wealth.slack.com/services/hooks/jenkins-ci/', channel: 'digital-technology', color: 'danger', message: "Build ${env.BUILD_TAG} FAILED at(<${env.BUILD_URL}|Jenkins Link>)", tokenCredentialId: 'slack-integration-digitaltech'
    }*/

    // try { // Enable this try and catch block if you want to send out email and slack notification
        /*stage("Deploy to environment") {
            node("$DEPLOY_SERVER") { // specify environment name as declared in Jenkins environment (check with DEVOPS)
                if (env.BRANCH_NAME == 'master') {
                    // must execute shell script in batch to ensure context is kept when sudo-ing to another user
                    sh """ sudo ${SERVER_USER} <<EOF
                    cd ${DEPLOY_PATH}/stable
                    rm -rf *.jar
                    ls -ltrh
                    wget --progress=bar:force ${NEXUS_URL_RELEASE}/com/cgi/wealth/${PROJECT_NAME}/${VERSION}/${PROJECT_NAME}-${VERSION}.jar -P ${DEPLOY_PATH}/stable/
EOF
                    """
                }
                if (env.BRANCH_NAME == 'develop') {
                    // retrieve metadata for JIRA as well as for retrieving the latest deployed artifacts
                    sh "curl -s '${NEXUS_URL_SNAPSHOT}/com/cgi/wealth/${PROJECT_NAME}/${VERSION}/maven-metadata.xml' >test"
                    sh 'grep "<value>.*</value>" test | uniq | sed -e "s/[</value>]//g" | tr -d " \t\n\r\f" > output'
                    def OUTPUT = readFile 'output'

                    // must execute shell script in batch to ensure context is kept when sudo-ing to another user
                    sh """ sudo ${SERVER_USER} <<EOF
                    cd ${DEPLOY_PATH}/latest
                    rm -rf *.jar
                    ls -ltrh
                    wget --progress=bar:force ${NEXUS_URL_SNAPSHOT}/com/cgi/wealth/${PROJECT_NAME}/${VERSION}/${PROJECT_NAME}-${OUTPUT}.jar -P ${DEPLOY_PATH}/latest/
EOF
                    """
                }
            }
        }*/
    /*} catch (Exception e) { // Enable this try and catch block if you want to send out email and slack notification
        currentBuild.result = "FAILED"
        emailext mimeType: 'text/html', subject: "BUILD ${env.BUILD_TAG} FAILED", body: "<p>Check build at: ${env.BUILD_URL}</p>", to: "$EMAIL_RECIPIENTS"
        // Slack channel to publish the notification to. Replace this url with the desired slack channel URL.
        slackSend baseUrl: 'https://cgi-global-wealth.slack.com/services/hooks/jenkins-ci/', channel: 'digital-technology', color: 'danger', message: "Build ${env.BUILD_TAG} FAILED at(<${env.BUILD_URL}|Jenkins Link>)", tokenCredentialId: 'slack-integration-digitaltech'
    }*/
}
