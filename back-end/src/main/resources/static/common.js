function excluirProprietario(cpf) {
    const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ cpf: cpf })
    };
    fetch('/excluir/cnh', requestOptions)
    .then(response => {
        if (response.ok) {
        console.log("apagou")
        } else {
        console.error("Erro ao excluir proprietário");
        }
    })
    .catch(error => console.error('Erro ao excluir proprietário', error));
}
function excluirVeiculo(placa) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ placa: placa })
      };
  
      fetch('/excluir/placa', requestOptions)
        .then(response => {
          if (response.ok) {
            exibirVeiculos(); // Atualizar a tabela após a exclusão
          } else {
            console.error('Erro ao excluir veiculo');
          }
        })
        .catch(error => console.error('Erro ao excluir veiculo', error));
}
function getMultas(placa){
    fetch(`/multas/${placa}`)
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Erro na resposta do servidor');
        }
        })
        .then(data => {
        
        if (Array.isArray(data) && data.length === 0) {
            return null;
        } else if (Array.isArray(data)) {
            return data;
        } else {
            throw new Error('Resposta do servidor não é um array válido');
        }
        })
        .catch(error => {
        console.log('Erro:', error);
    });
}
function cadastrarMulta(identificador, multa){
        fetch(`/cadastrar/multa?identificador=${identificador}`, {
            method: 'POST',
            headers: {
            'Content-Type': 'application/json',
            },
            body: JSON.stringify([multa])
        })
        .then(response => {
            if (response.ok) {
            // Sucesso no cadastro da multa
            alert('Multa cadastrada com sucesso!');
            // Redirecionar para outra página após o cadastro
            window.location.href = 'register-fine.html';
            } else {
            // Falha no cadastro da multa
            alert('Erro ao cadastrar multa. Veículo não encontrado.');
            }
        })
        .catch(error => {
            console.error('Erro ao cadastrar multa:', error);
        });
}