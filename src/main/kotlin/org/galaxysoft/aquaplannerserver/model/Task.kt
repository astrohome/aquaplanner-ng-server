package org.galaxysoft.aquaplannerserver.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class Task(@Id @GeneratedValue val id: Int, val channel: Int)