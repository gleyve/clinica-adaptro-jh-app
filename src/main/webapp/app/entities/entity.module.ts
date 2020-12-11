import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'fornecedor',
        loadChildren: () => import('./fornecedor/fornecedor.module').then(m => m.ClinicaAdaptrojhAppFornecedorModule),
      },
      {
        path: 'cliente',
        loadChildren: () => import('./cliente/cliente.module').then(m => m.ClinicaAdaptrojhAppClienteModule),
      },
      {
        path: 'plano-estrategico',
        loadChildren: () => import('./plano-estrategico/plano-estrategico.module').then(m => m.ClinicaAdaptrojhAppPlanoEstrategicoModule),
      },
      {
        path: 'especialidade-saude',
        loadChildren: () =>
          import('./especialidade-saude/especialidade-saude.module').then(m => m.ClinicaAdaptrojhAppEspecialidadeSaudeModule),
      },
      {
        path: 'funcionario',
        loadChildren: () => import('./funcionario/funcionario.module').then(m => m.ClinicaAdaptrojhAppFuncionarioModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class ClinicaAdaptrojhAppEntityModule {}
