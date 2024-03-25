module Stack ( Stack, newS, freeCellsS, stackS, netS, netW, holdsS, popS)
 where

import Container
import Route

data Stack = Sta [ Container ] Int deriving (Eq, Show) 

newS :: Int -> Stack  -- construye una pila con la capacidad indicada
newS capacity
    | capacity <= 0 = error "No hay capacidad para crear la nueva pila"
    | otherwise = Sta [] capacity

freeCellsS :: Stack -> Int                    -- responde la celdas disponibles en la pila
freeCellsS (Sta containers capacity) = capacity - length containers

stackS :: Stack -> Container -> Stack   -- apila el contenedor indicado en la pila
stackS (Sta containers capacity) container
    | freeCellsS (Sta containers capacity) /= 0 = Sta (containers ++ [container]) capacity
    | otherwise = Sta containers capacity

netS :: Stack -> Int                          -- responde el peso neto de los contenedores en la pila
netS (Sta containers _) = sum (map netC containers)

netW :: Stack -> Container -> Bool  -- indica si al agregar un container a la pila esta sobrepasa el peso permitido.
netW stack container = (netS stack) + (netC container) <= 20 

holdsS :: Stack -> Container -> Route -> Bool
holdsS (Sta containers capacity) container route
    | length containers == 0 = True
    | otherwise = freeCellsS (Sta containers capacity) > 0 && inOrderR route (destinationC container) (destinationC (last containers)) && netW (Sta containers capacity) container

nuevaLista :: [Container] -> String -> [Container] -- crea una nueva lista con las contenedores que restan, sacando los de la ciudad dada.
nuevaLista containers s = take (length containers - length [destinationC y|y<-containers, destinationC y == s]) containers

popS :: Stack -> String -> Stack  -- quita del tope los contenedores con destino en la ciudad indicada
popS (Sta containers i) s = Sta (nuevaLista containers s) i 