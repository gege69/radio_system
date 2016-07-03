<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="modal fade" id="myModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Alterar dados da Música</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="altera-nome-midia-form" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <input type="hidden" id="idMidia" name="idMidia" value="0">
          
          <div class="row">
            <div class="col-lg-12 col-md-12">

              <div class="form-group">
                <label for="login" class="control-label col-sm-2 col-md-2">Nome</label>
                <div class="col-lg-8 col-md-10">
                  <input type="text" class="form-control" id="nomeMidia" name="nome">
                </div>
              </div>

              <div class="form-group">
                <label for="login" class="control-label col-sm-2 col-md-2">Descrição</label>
                <div class="col-lg-8 col-md-10">
                  <input type="text" class="form-control" id="descricaoMidia" name="descricao">
                </div>
              </div>

              <div class="form-group">
                <label for="login" class="control-label col-sm-2 col-md-2">Artista</label>
                <div class="col-lg-8 col-md-10">
                  <input type="text" class="form-control" id="artistaMidia" name="artist">
                </div>
              </div>

              <h4>Gêneros da música</h4>

              <div class="container col-md-12" id="viewContainerModal">
              </div>

            </div> 
          </div>
          
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
        <button type="button" class="btn btn-primary" id="btnSalvar">Alterar</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<div class="modal fade" id="myModalGenerosGeral">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Alterar Gêneros de <span class="qtdMusicasGeneros"></span> Música(s)</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="altera-nome-midia-form" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="alert alert-warning" id="divWarningGeneros" role="alert"><strong>Atenção!</strong> Os gêneros escolhidos aqui serão atribuídos para TODAS as músicas selecionadas pela pesquisa atual.</div>
            </div>
          </div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">

              <h4>Gêneros da(s) <span class="qtdMusicasGeneros"></span> música(s) listada(s)</h4>

              <div class="container col-md-12" id="viewContainerGenerosGeralModal">
              </div>

            </div> 
          </div>
          
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
        <button type="button" class="btn btn-primary" id="btnSalvarGenerosGeral">Alterar</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="myDialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Remover Música</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="remove-midia-form" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <input type="hidden" id="idMidiaDialog" name="idMidia" value="0">
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <h4><span id="nomeMusica"></span></h4>
            </div>
          
            <div class="spacer-vertical10"></div>

            <div class="col-lg-12 col-md-12">
              Deseja realmente remover essa Música?
            </div> 
          </div>
          
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="btnNaoDialog" data-dismiss="modal">Não</button>
        <button type="button" class="btn btn-primary" id="btnConfirmarDelete">Sim</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

