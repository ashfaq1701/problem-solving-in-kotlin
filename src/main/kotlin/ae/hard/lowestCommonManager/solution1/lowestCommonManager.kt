package ae.hard.lowestCommonManager.solution1

class OrgChart(name: Char) {
    val name = name
    val directReports = mutableListOf<OrgChart>()
}

fun getLowestCommonManager(topManager: OrgChart, reportOne: OrgChart, reportTwo: OrgChart): OrgChart? {
    return getOrgInfo(topManager, reportOne, reportTwo).lowestCommonManager
}

data class OrgInfo(val lowestCommonManager: OrgChart?, val numberOfImportantReports: Int)

fun getOrgInfo(manager: OrgChart, reportOne: OrgChart, reportTwo: OrgChart): OrgInfo {
    var numberOfImportantReports = 0

    for (directReport in manager.directReports) {
        val reportOrgInfo = getOrgInfo(directReport, reportOne, reportTwo)
        if (reportOrgInfo.lowestCommonManager != null) {
            return reportOrgInfo
        }
        numberOfImportantReports += reportOrgInfo.numberOfImportantReports
    }

    if (manager == reportOne || manager == reportTwo) {
        numberOfImportantReports += 1
    }

    val lowestCommonManager = if (numberOfImportantReports == 2) manager else null

    return OrgInfo(lowestCommonManager, numberOfImportantReports)
}