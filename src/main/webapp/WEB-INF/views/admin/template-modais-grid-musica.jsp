<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div class="modal" id="modalEditar">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Alterar dados da Música</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="altera-nome-midia-form" method="POST">
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



<div class="modal" id="modalGeneros">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Alterar Gêneros de <span class="qtdMusicasGeneros"></span> Música(s)</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="altera-nome-midia-form" method="POST">
          
          <div class="row">
            <div class="col-lg-12 col-md-12" id="alertAreaModal">
            </div>
          </div>

          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="alert alert-warning" id="divWarningGeneros" role="alert">
                <strong>Atenção!</strong> Os gêneros escolhidos aqui serão atribuídos para TODAS as músicas selecionadas.<br/>
                <span class="tamanhoSelecao"></span> música(s) selecionada(s).
              </div>

            </div>
          </div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">

              <h4>Atribuir Gêneros para <span class="tamanhoSelecao"></span> música(s) selecionada(s)</h4>

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


<div class="modal" id="dialogRemover">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Remover Música</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="remove-midia-form" method="POST">
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




<div class="modal" id="dialogDeletarSelecao">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Remover <span class="tamanhoSelecao"></span> Música(s)</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="remove-midia-form" method="POST">

          <div class="row">
            <div class="loader" id="ajaxload" style="display : none;"></div>
          </div>

          <div class="spacer-vertical10"></div>

          <div class="row">
            <div class="col-lg-12 col-md-12" id="alertAreaModalDeletar">
            </div>
          </div>

          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="alert alert-danger" id="divWarningGeneros" role="alert">
                <i class="material-icons md-48 animated infinite bounce">error</i>
                &nbsp;Deseja realmente remover a(s) <span class="tamanhoSelecao"></span> música(s) selecionada(s)?
              </div>
            </div>
          </div>
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Não</button>
        <button type="button" class="btn btn-default" id="btnConfirmarDeleteSelecao">Sim</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<div class="modal" id="dialogConverter">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Converter Música</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="remove-midia-form" method="POST">
          <input type="hidden" id="idMidiaConverterDialog" name="idMidia" value="0">
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <h4><span id="nomeMusicaConverter"></span></h4>
            </div>
          
            <div class="spacer-vertical10"></div>

            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-2">BitRate</label>
              <div class="col-lg-8 col-md-10">
                <select class="form-control" id="bitRate" name="bitRate">
                  <option value="AVERAGE" selected="selected" >BitRate Médio (ABR)</option>
                  <option value="CONSTANT" >BitRate Constante (CBR)</option>
                  <option value="VARIABLE" >BitRate Variável (VBR)</option>
                </select>
              </div>
            </div>
            
            <div class="form-group" id="divVariableBR" style="display: none;">
              <label for="login" class="control-label col-sm-2 col-md-2">Valor do BitRate VARIÁVEL</label>
              <div class="col-lg-8 col-md-10">
                <select class="form-control" id="variableBitRate" name="variableBitRate">
                </select>
              </div>
            </div>
            
            <div class="form-group" id="divBR">
              <label for="login" class="control-label col-sm-2 col-md-2">Valor do BitRate</label>
              <div class="col-lg-8 col-md-10">
                <input type="text" class="form-control" id="valorBitRate" name="valorBitRate">
              </div>
            </div>
          </div>
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="btnNaoDialog" data-dismiss="modal">Não</button>
        <button type="button" class="btn btn-primary" id="btnConfirmarConverter">Sim</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


