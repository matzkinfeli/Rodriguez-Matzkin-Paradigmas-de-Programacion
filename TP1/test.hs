import Container 
import Stack
import Vessel
import Route
import Data.Typeable

import Control.Exception
import System.IO.Unsafe

testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()


mdq = "MDQ"

rsl = "RSL"

bhi = "BHI"

bue = "BUE"

qeq = "QEQ"

mvd = "MVD"

route = newR [ bhi, qeq, mdq, bue, rsl ]

container1 = newC bhi 20

container2 = newC qeq 3

container3 = newC mdq 12

container4 = newC bue 9

container5 = newC rsl 15

container6 = newC rsl 1


testContainer = [ 

    not (testF (newC bhi 10)),                            -- Crear Container con peso <= 20 y > 0 

    not (testF (newC bhi 40)),                            -- Crear Container con peso > 20 

    not (testF (newC bhi (-1))),                          -- crear Container con peso < 0

    not (testF (newC bhi 0)),                             -- crear Container con peso = 0

    destinationC container5 == rsl,                       -- chequea que la ciudad de destino del container 5 sea rsl

    netC container5 == 15                                 -- chequea que el peso del container 5 sea == 15

    ] 


testRuta = [

    not (testF (newR [ bhi, qeq, mdq, bue, rsl ])),        -- chequea si la ruta se creo bien 

    inOrderR route bhi qeq,                                -- chequea si funciona la funcion inOrderR 

    not (inOrderR route rsl mdq),                          -- chequea si funciona inOrderR (si no estan en orden deberia dar True)

    testF(inOrderR route bhi "RIO"),                       -- tira error si una ciudad no esta en la ruta 

    not (testF(newR []))                                   -- creo una ruta vacia, tendria que dar error

    ]

emptyStack1 = newS 1                                       -- Stack con 1 espacio vacio

fullStack1 = stackS emptyStack1 container2                 -- Stack con 1 espacio 

emptyStack2 = newS 2                                       -- stack con 2 espacios vacios

stack2container4 = stackS emptyStack2 container4           -- stack con un contenedor destino bue

stack2container2 = stackS emptyStack2 container2           -- stack con un contenedor con peso 3

fullStack2 = stackS stack2container2 container4            -- stack lleno con peso 12

unloadStack2 = popS fullStack2 bue                         -- stack anterior si le saco el container de bue


testStack = [ 

    not(testF(stackS emptyStack1 container2)),             -- Chequea que se pueda apilar un contenedor cuando hay espacio

    not(holdsS fullStack1 container3 route),               -- comprueba si se puede apilar un contenedor en un stack lleno 

    not(holdsS stack2container4 container5 route),         -- comprueba si se puede apilar un contenedor sobre otro con un destino anterior

    not(holdsS stack2container2 container3 route),         -- comprueba si se puede apilar un contenedor sobre otro cuando el peso es > 20 

    netS stack2container2 == 3,                            -- comprueba si el peso del stack es el que tendria que ser 

    netS fullStack2 == 12,                                 -- comprueba si el peso del stack es el que tendria que ser luego de aplicar stackS

    netS unloadStack2 == 3,                                -- chequeo si el peso del stack es efectivamente el que tendria que ser luego de aplicar popS

    popS fullStack2 rsl == fullStack2                      -- chequeo que popS no haga nada cuando le paso una ciudad que no esta en el stack

    ]   


vessel1EmptyStack = newV 1 1 route                         -- barco con un stack vacio

vessel1FullStack = loadV vessel1EmptyStack container4      -- barco con un stack lleno, peso del container 9, destino bue

vessel2Height = newV 2 1 route                             -- barco con un stack vacio de 2 de altura 

vessel1Free = loadV vessel2Height container2               -- barco con un container en un stack de 2 de altura, peso del container 3, destino qeq

fullVessel = loadV vessel1Free container4                  -- barco con un stack de 2 de altura lleno, peso 12 y ultimo container destino bue

vesselContainer4 = unloadV fullVessel bue                  -- le saco el container 4 a vessel1Free

vessel1Stack3 = newV 3 1 route                             -- barco un stack 3 altura

vessel1Stack2 = loadV vessel1Stack3 container6             -- barco con un stack, 2 lugares libres, peso del container 1, destino rsl

vessel1Stack1 = loadV vessel1Stack2 container4             -- barco con un stack, 1 lugar libre. primer container con peso 1 destino rsl, segundo container con peso 9 destino bue

vessel1Stack0 = loadV vessel1Stack2 container4             -- barco sin lugar libre. primer container con peso 1 destino bue, segundo y tercer container con peso 9 destino bue

vessel3Stack3 = newV 1 3 route                             -- barco con 3 stacks con 1 de altura 

vessel3Stack2 = loadV vessel3Stack3 container5             -- Se le carga al barco un container destino rsl peso 15

vessel3Stack1 = loadV vessel3Stack2 container4             -- Se le carga al barco un container destino bue peso 9



testVessel = [

    not (testF (newV 1 1 route)),                          -- base
    
    testF (newV 0 1 route),                                -- Chequea que no se pueda crear un barco con 0 stacks

    testF (newV 2 (-3) route),                             -- Chequea que no se pueda crear un barco con stacks negativas

    testF (newV 0 1 route),                                -- Chequea que no se pueda crear un barco con stacks de altura 0

    testF (newV (-4) 1 route),                             -- Chequea que no se pueda crear un barco con stacks de altura negativa

    testF (netV(loadV vessel1FullStack container5)),       -- chequea que el stack este lleno

    not (testF (netV(loadV vessel1Free container1))),      -- chequea si el barco se pasa de peso

    netV fullVessel == 12,                                 -- chequea si el peso del barco es igual a la suma del peso de los containers

    freeCellsV vessel1Stack2 == 2,                         -- chequea mediante los stacks si el barco se cargó bien

    freeCellsV vessel1FullStack /= freeCellsV (unloadV vessel1FullStack bue),  -- Chequea que la función unloadV descargue un contenedor en la ciudad correcta

    vessel1EmptyStack == unloadV vessel1EmptyStack mdq,    -- chequea que al llamar unloadV a un barco con un stack vacío, devuelve el mismo barco con el stack vacío

    vessel1FullStack == unloadV vessel1FullStack mdq       -- Chequea que al llamar unloadV en una ciudad sobre la cual el barco no tiene contenedores, no se produzcan cambios

    ]
