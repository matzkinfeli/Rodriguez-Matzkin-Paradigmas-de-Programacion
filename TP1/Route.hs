    module Route ( Route, newR, inOrderR )
    where
    import Data.List

    data Route = Rou [ String ] deriving (Eq, Show)

    newR :: [ String ] -> Route                    -- construye una ruta segun una lista de ciudades
    newR = Rou 

    inOrderR :: Route -> String -> String -> Bool   -- indica si la primer ciudad consultada esta antes que la segunda ciudad en la ruta.
    inOrderR (Rou cities) city1 city2
        | notElem city1 cities || notElem city2 cities = error "alguna de las ciudades no esta en la lista"
        | (Just idx1, Just idx2) <- (elemIndex city1 cities, elemIndex city2 cities) = inOrder (Just idx1) (Just idx2)
        | otherwise = False

    inOrder :: Maybe Int -> Maybe Int -> Bool
    inOrder (Just idx1) (Just idx2)
        | idx1 < idx2 = True
        | idx1 == idx2 = True
        | otherwise = False
    inOrder _ _ = False
