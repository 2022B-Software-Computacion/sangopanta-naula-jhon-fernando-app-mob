package com.example.a02_android

class JCitiesDTO(
    public var name: String?,
    public var state: String?,
    public var country: String?,
    public var capital: Boolean?,
    public var population: Long?,
    public var regions: List<String>?,
) {

    override fun toString(): String{
        return "${name} - ${country}"
    }
}