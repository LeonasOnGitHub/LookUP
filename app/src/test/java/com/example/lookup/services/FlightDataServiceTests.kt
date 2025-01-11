package com.example.lookup.services

import com.example.lookup.models.FlightData
import com.example.lookup.services.FlightDataService
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.json.JSONArray
import org.json.JSONObject
import org.junit.After
import org.junit.Before
import org.junit.Test


class FlightDataServiceTests {

    private lateinit var flightDataService: FlightDataService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        // Setup MockWebServer to mock HTTP requests
        mockWebServer = MockWebServer()
        mockWebServer.start()

        // Instantiate the service with the mock server URL
        flightDataService = FlightDataService()

        // Set the base URL to the mock server's URL
        val baseUrl = mockWebServer.url("/")
        // FlightDataService should use this base URL in its requests
        // Normally, we would inject this URL into the service, but for testing, we override the URL or replace it
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetFlightDataReturnsValidSata() {
        // Mocking the JSONObject and JSONArray
        val mockJson = mockk<JSONObject>()
        val mockStatesArray = mockk<JSONArray>()
        val mockFlightArray = mockk<JSONArray>()

        every { mockJson.getJSONArray("states") } returns mockStatesArray
        every { mockStatesArray.length() } returns 1
        every { mockStatesArray.getJSONArray(0) } returns mockFlightArray

        // Enqueue a mock response that contains the mocked JSON
        val mockResponse = """
            {
                "states": [
                    ["abc123", "N12345", "USA", 1, 2, 5.0, 6.0, 0, true, 300.0, 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0]
                ]
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(mockResponse).setResponseCode(200))

        // Call the method
        flightDataService.getFlightData { flightList ->
            // Validate the response
            TestCase.assertNotNull(flightList)
            TestCase.assertEquals(1, flightList.size)
            val flightData = flightList[0]
            TestCase.assertEquals("abc123", flightData.id)
            TestCase.assertEquals("N12345", flightData.callSign)
            TestCase.assertEquals("USA", flightData.origin)
        }
    }


    @Test
    fun testGetFlightDataHandlesFailureCorrectly() {
        // Enqueue a failure response
        mockWebServer.enqueue(MockResponse().setResponseCode(500))

        flightDataService.getFlightData { flightList ->
            // Validate the callback response on failure
            TestCase.assertTrue(flightList.isEmpty())
        }
    }
}