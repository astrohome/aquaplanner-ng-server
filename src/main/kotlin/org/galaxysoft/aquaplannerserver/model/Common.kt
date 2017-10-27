package org.galaxysoft.aquaplannerserver.model

data class SelectOption<out ID, out VALUE>(val id: ID, val text: VALUE)
