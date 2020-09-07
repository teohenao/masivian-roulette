## masivian-roulette

# Instrucciones
- Se debe tener instalado redis server
- Descargar el repositorio
- Importar el proyecto en un IDE tal como SpringBootSuite
- Click derecho sobre el projecto -> maven -> maven update project
- Click derecho run -> Spring boot app

# Servicios
- Crear una nueva ruleta -> http://localhost:8080/ruleta/crear
- Obtener todas las ruletas -> http://localhost:8080/ruleta/obtenerruletas
- Abrir una ruleta -> http://localhost:8080/ruleta/abrirruleta/"ID_RULETA"
- Apostar en una ruleta -> http://localhost:8080/ruleta/apostar/"ID_RULETA"  
  body -> 
  valor = "Color "Negro,Rojo" o numero del 0 hasta el 36
  cantidad = valor apuesta
  header -> 
  user : "USER"
- Cerrrar la ruleta -> http://localhost:8080/ruleta/cerrarruleta/"ID_RULETA"
