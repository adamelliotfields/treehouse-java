package io.github.adamelliotfields.service

import com.github.fakemongo.Fongo
import com.mongodb.MongoClient
import io.github.adamelliotfields.entity.Course
import io.github.adamelliotfields.entity.Review
import lombok.Getter
import org.mongodb.morphia.Datastore
import org.mongodb.morphia.Morphia

class FongoService private constructor() {

    @Getter
    val fongo: Fongo

    @Getter
    val fongoClient: MongoClient

    @Getter
    val morphia: Morphia

    @Getter
    val datastore: Datastore

    init {
        fongo = Fongo("test")
        morphia = Morphia()
        fongoClient = fongo.mongo

        morphia.map(Course::class.java, Review::class.java)

        datastore = morphia.createDatastore(fongoClient, "test")
    }

    companion object {
        @Getter
        val instance = FongoService()
    }
}
