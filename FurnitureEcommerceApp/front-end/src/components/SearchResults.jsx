import React, { useEffect, useState } from 'react'
import { FurnitureApi } from '../data/FurnitureApi'
import ItemContainer from './ItemContainer'

import './css/SearchResults.css'

const SearchResults = ({ user, setUser, searchValue, setRedirect }) => {
    const [items, setItems] = useState([])

    // Set up default search value if navigated to directly
    const setDefaultSearchValue = () => {
        const queryParameters = new URLSearchParams(window.location.search)
        searchValue = queryParameters.get("search")
        if (!searchValue) {
            searchValue = ""
        }
    }
    setDefaultSearchValue()

    // Listen to changes in the search value
    useEffect(() => {
        const loadItems = async () => {
            const itemsFromSearch = await FurnitureApi.getItemsByQueryString(searchValue)
            setItems(itemsFromSearch)
        }

        loadItems()
    }, [user, searchValue])

  return (
    <div id="search-results">
        <h3>Search Results {`(${items.length})`}</h3>\
        
        <ItemContainer user={user} setUser={setUser} setRedirect={setRedirect} items={items}/> 
    </div>
  )
}

export default SearchResults
