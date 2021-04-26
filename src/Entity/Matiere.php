<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Matiere
 *
 * @ORM\Table(name="matiere")
 * @ORM\Entity
 */
class Matiere
{
    /**
     * @var int
     *
     * @ORM\Column(name="idMatiere", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idmatiere;

    /**
     * @var string
     *
     * @ORM\Column(name="nomMatiere", type="string", length=25, nullable=false)
     */
    private $nommatiere;

    /**
     * @var int
     *
     * @ORM\Column(name="niveau", type="integer", nullable=false)
     */
    private $niveau;

    /**
     * @return int
     */
    public function getIdmatiere(): int
    {
        return $this->idmatiere;
    }

    /**
     * @param int $idmatiere
     */
    public function setIdmatiere(int $idmatiere): void
    {
        $this->idmatiere = $idmatiere;
    }

    /**
     * @return string
     */
    public function getNommatiere(): string
    {
        return $this->nommatiere;
    }

    /**
     * @param string $nommatiere
     */
    public function setNommatiere(string $nommatiere): void
    {
        $this->nommatiere = $nommatiere;
    }

    /**
     * @return int
     */
    public function getNiveau(): int
    {
        return $this->niveau;
    }

    /**
     * @param int $niveau
     */
    public function setNiveau(int $niveau): void
    {
        $this->niveau = $niveau;
    }


}
