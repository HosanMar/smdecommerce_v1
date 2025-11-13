/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.produto;

/**
 *
 * @author gal
 */
public class Produto {
    private Integer id;
   private String descricao;
   private Double preco;

   public Produto() {
   }

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getDescricao() {
      return this.descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public Double getPreco() {
      return this.preco;
   }

   public void setPreco(Double preco) {
      this.preco = preco;
   }
}
