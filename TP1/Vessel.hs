module Vessel ( Vessel, newV, freeCellsV, loadV, unloadV, netV, dispStack)
 where

import Container
import Stack
import Route

data Vessel = Ves [ Stack ] Route deriving (Eq, Show)

newV :: Int -> Int -> Route -> Vessel  -- crea un nuevo barco
newV stacks height route | stacks <= 0 || height <= 0 = error "no puede haber un barco sin bahias"
                         | otherwise = Ves [newS height |y <- [1..stacks]]route                           

freeCellsV :: Vessel -> Int            -- responde la celdas disponibles en el barco
freeCellsV (Ves stacks _) = sum (map freeCellsS stacks)


dispStack :: [Stack] -> Container -> Route -> [Stack]  -- busca el stack disponible para cargar el container  
dispStack [] _ _ = []
dispStack (stack:stacks) container route
    | holdsS stack container route = stackS stack container : stacks
    | otherwise = stack : dispStack stacks container route 


loadV :: Vessel -> Container -> Vessel -- carga un contenedor en el barco 
loadV (Ves stacks route) container 
    | dispStack stacks container route == stacks = error "no hay stacks disponibles"
    | otherwise = Ves (dispStack stacks container route) route


unloadV :: Vessel -> String -> Vessel  -- responde un barco al que se le han descargado los contenedores que podían descargarse en la ciudad
unloadV (Ves stacks route) city = Ves (map (\stack -> popS stack city) stacks) route


netV :: Vessel -> Int                  -- responde el peso neto en toneladas de los contenedores en el barco
netV (Ves stacks _) = sum (map netS stacks)


