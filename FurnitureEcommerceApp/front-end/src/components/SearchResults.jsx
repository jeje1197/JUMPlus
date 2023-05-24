import React, { useEffect, useState } from 'react'
import { FurnitureApi } from '../data/FurnitureApi'
import ItemContainer from './FeaturedItems'

import './css/SearchResults.css'

const SearchResults = ({ user, searchValue }) => {
    const [items, setItems] = useState([])

    // Set up default search value if navigated to directly
    const setDefaultSearchValue = () => {
        const queryParameters = new URLSearchParams(window.location.search)
        searchValue = queryParameters.get("search")
    }
    setDefaultSearchValue()

    // Listen to changes in the search value
    useEffect(() => {
        const loadItems = async () => {
            const itemsFromSearch = await FurnitureApi.getItemsByQueryString(searchValue)
            setItems(itemsFromSearch)
        }

        loadItems()
    }, [searchValue])

  return (
    <div id="search-results">
        <h3>Items Found: ({items.length})</h3>
        <hr />
        <ItemContainer items={items}/>
    </div>
  )
}

export default SearchResults
