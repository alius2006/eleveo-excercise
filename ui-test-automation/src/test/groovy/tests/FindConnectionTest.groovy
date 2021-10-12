package tests

import dto.ConnectionDto
import pages.FoundConnectionsPage
import pages.LandingPage

class FindConnectionTest extends AbstractBaseTest {

    def "Search for a direct connection between Ostrava and Brno"() {

        given: "Get test data from DTO"
        def testConnection = ConnectionDto.getNextMondayDto()

        when: "Fill in the form and press Search button"
        LandingPage.findAConnection(testConnection)

        then: "Print the first direct connection's details"
        FoundConnectionsPage.printFirstDirectConnectionInfo()

        and: "Verify all direct connections details"
        FoundConnectionsPage.verifyAllDirectConnections(testConnection)

        and: "Print the lowest price found"
        FoundConnectionsPage.printLowestPrice()
    }
}
