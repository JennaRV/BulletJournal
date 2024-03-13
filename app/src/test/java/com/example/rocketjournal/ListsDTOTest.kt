package com.example.rocketjournal

import com.example.rocketjournal.model.DataTransferObjects.ListsDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import org.junit.Assert.assertEquals
import org.junit.Test

class ListsDTOTest {

    private val json = Json { prettyPrint = true }

    @Test
    fun `serialize ListsDTO to JSON`() {
        val listsDTO = ListsDTO(
            list_id = 1,
            user_id = 100,
            name = "Test List",
            is_complete = false
        )
        val expectedJson = """{"list_id":1,"user_id":100,"name":"Test List","is_complete":false}"""
        val actualJson = Json.encodeToString(ListsDTO.serializer(), listsDTO)

        assertEquals(expectedJson, actualJson)
    }

    @Test
    fun `deserialize JSON to ListsDTO`() {
        val jsonString = """{"list_id":1,"user_id":100,"name":"Test List","is_complete":false}"""
        val listsDTO = json.decodeFromString<ListsDTO>(jsonString)

        assertEquals(1, listsDTO.list_id)
        assertEquals(100, listsDTO.user_id)
        assertEquals("Test List", listsDTO.name)
        assertEquals(false, listsDTO.is_complete)
    }
}
