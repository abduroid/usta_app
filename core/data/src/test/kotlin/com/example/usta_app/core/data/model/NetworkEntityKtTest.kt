package com.example.usta_app.core.data.model

import com.example.usta_app.core.network.model.NetworkAppointment
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import kotlin.test.assertEquals

class NetworkEntityKtTest {

    @Test
    fun network_appointments_can_be_mapped_to_appointment_entity() {
        val testCreatedDateTime = LocalDateTime.of(2024,3,12, 0,0,0)
        val testSelectedDate = LocalDate.of(2024,3,12)
        val networkModel = NetworkAppointment(
            id = "189",
            barberId = "289",
            clientId = "1999",
            clientFirstName = "Abdulaziz",
            clientLastName = "Abduqodirov",
            createdDateTime = testCreatedDateTime,
            selectedDate = testSelectedDate,
            startTime = 10f,
            endTime = 10.5f,
        )
        val entity = networkModel.asEntity()

        assertEquals("189", entity.id)
        assertEquals("1999", entity.clientId)
        assertEquals("Abdulaziz", entity.clientFirstName)
        assertEquals("Abduqodirov", entity.clientLastName)
//        assertEquals(testCreatedDateTime, entity.createdDateTime)
//        assertEquals(testSelectedDate, entity.selectedDate)
        assertEquals(10f, entity.startTime)
        assertEquals(10.5f, entity.endTime)
    }
}
