package com.assignment.controller

import com.assignment.domain.Comedian
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification
import spock.lang.Unroll

import java.nio.charset.StandardCharsets

@SpringBootTest
@AutoConfigureMockMvc
@Unroll
class ComediansControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    def "GET all"() {
        given:
        def comedians = new ArrayList<Comedian>() {
            {
                add(new Comedian(1, "ビートたけし"))
                add(new Comedian(2, "明石家さんま"))
                add(new Comedian(3, "タモリ"))
                add(new Comedian(4, "田中裕二"))
                add(new Comedian(5, "太田光"))
            }
        }
        def jsonBuilder = new JsonBuilder(comedians)
        def jsonSlurper = new JsonSlurper()

        when:
        def actual = mockMvc.perform(MockMvcRequestBuilders.get("/comedians")).andReturn().getResponse()

        then:
        actual.getStatus() == HttpStatus.OK.value()
        jsonSlurper.parseText(actual.getContentAsString(StandardCharsets.UTF_8)) ==
                jsonSlurper.parseText(jsonBuilder.toPrettyString())

    }

    def "GET by id"(id, name) {
        given:
        def comedian = new Comedian(id, name)
        def jsonBuilder = new JsonBuilder(comedian)
        def jsonSlurper = new JsonSlurper()

        when:
        def actual = mockMvc.perform(MockMvcRequestBuilders.get("/comedians/" + id.toString())).
                andReturn().getResponse()

        then:
        actual.getStatus() == HttpStatus.OK.value()
        jsonSlurper.parseText(actual.getContentAsString(StandardCharsets.UTF_8)) ==
                jsonSlurper.parseText(jsonBuilder.toPrettyString())

        where:
        id | name
        1  | "ビートたけし"
        2  | "明石家さんま"
        3  | "タモリ"
        4  | "田中裕二"
        5  | "太田光"
    }

    def "DELETE by id"() {
        given:
        // サンプルで3
        def id = 3

        when:
        def res = mockMvc.perform(MockMvcRequestBuilders.delete("/comedians/" + id.toString())).andReturn().getResponse()
        def confirm =
                mockMvc.perform(MockMvcRequestBuilders.get("/comedians/" + id.toString()))
                        .andReturn().getResponse()

        then:
        res.getStatus() == HttpStatus.NO_CONTENT.value()
        confirm.getContentAsString(StandardCharsets.UTF_8) == ""
    }

    def "POST"() {
        given:
        def params = ["上田晋也", "有田哲平"]

        def jsonSlurper = new JsonSlurper()

        when:
        def actual = mockMvc.perform(MockMvcRequestBuilders.post("/comedians")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andReturn().getResponse()
        def confirm =
                mockMvc.perform(MockMvcRequestBuilders.get("/comedians"))
                        .andReturn().getResponse()

        then:
        actual.getStatus() == HttpStatus.CREATED.value()
        def str = jsonSlurper.parseText(confirm.getContentAsString(StandardCharsets.UTF_8)).toString()
        str.contains(params[0])
        str.contains(params[1])
    }

    def "PUT"() {
        given:
        def comedian = new Comedian(1, "松本人志")

        def jsonSlurper = new JsonSlurper()
        def jsonBuilder = new JsonBuilder(comedian)

        when:
        def res = mockMvc.perform(MockMvcRequestBuilders.put("/comedians")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(comedian)))
                .andReturn().getResponse()
        def confirm =
                mockMvc.perform(MockMvcRequestBuilders.get("/comedians/" + comedian.id.toString()))
                        .andReturn().getResponse()

        then:
        res.getStatus() == HttpStatus.NO_CONTENT.value()
        jsonSlurper.parseText(confirm.getContentAsString(StandardCharsets.UTF_8)) ==
                jsonSlurper.parseText(jsonBuilder.toPrettyString())
    }

}
